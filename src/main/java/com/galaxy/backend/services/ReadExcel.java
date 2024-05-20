package com.galaxy.backend.services;

import com.galaxy.backend.dtos.SaldoCorretorDTO;
import com.galaxy.backend.dtos.SaldoSeguradoraDTO;
import com.galaxy.backend.models.Corretor;
import com.galaxy.backend.models.Seguradora;
import org.apache.commons.collections4.list.TreeList;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.regex.Pattern;

@Service
public class ReadExcel {
    @Autowired
    private CorretorService corretorService;

    private static final DataFormatter formatter = new DataFormatter();
    private static final Pattern pattern = Pattern.compile("[^a-zA-Z]");

    /**
     * Método para ler um arquivo Excel e processar seus dados.
     *
     * @param tempFile O caminho do arquivo Excel a ser lido.
     */
    public List<SaldoCorretorDTO> readExcel(File tempFile) {
        try(FileInputStream file = new FileInputStream(tempFile)) {
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Map<String, List<Row>> map = processSheet(sheet);
            List<SaldoCorretorDTO> saldoCorretores = new ArrayList<>();

            map.forEach((corretor, rows) -> saldoCorretores.add(processRows(corretor, rows)));
            return saldoCorretores;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Arquivo nao encontrado");
            return Collections.emptyList();
        }
    }

    /**
     * Processa a planilha do Excel para identificar e agrupar intervalos de linhas.
     *
     * @param sheet A planilha a ser processada.
     * @return Um mapa de intervalos de linhas agrupadas por corretor.
     */
    private Map<String, List<Row>> processSheet(HSSFSheet sheet) {
        Map<String, List<Row>> dataMap = new TreeMap<>();
        List<Row> lastRows = new ArrayList<>();
        int startRow = 2;
        int endRow;

        // Identifica os intervalos de linhas
        for (Row row : sheet) {
            if (row.getPhysicalNumberOfCells() == 1) {
                lastRows.add(row);
            }
        }

        // Processa os intervalos de linhas e adicionar ao mapa
        for (Row lastRow : lastRows) {
            endRow = lastRow.getRowNum();
            Row nextRow = sheet.getRow(endRow + 1);
            var num = nextRow.getLastCellNum() - 1;
            Cell cell = nextRow.getCell(num);
            String corretor = pattern.matcher(cell.getStringCellValue()).replaceAll("");

            switch (corretor) {
                case "IGOMATOS" -> corretor = "IGO MATOS";
                case "REDEBRASIL" -> corretor = "REDE BRASIL";
                case "SEMCORRETOR" -> corretor = "SEM CORRETOR";
                case "zJosimar" -> corretor = "JOSIMAR";
            }

            List<Row> rows = new ArrayList<>();
            for (int j = startRow; j < endRow; j++) {
                rows.add(sheet.getRow(j));
            }
            dataMap.put(corretor, rows);
            startRow = endRow + 3;
        }
        return dataMap;
    }

    /**
     * Processa as linhas de um relatório para calcular saldo e quantidade por seguradora.
     *
     * @param nomeCorretor O nome do corretor associado às linhas.
     * @param rows     As linhas a serem processadas.
     */
    private SaldoCorretorDTO processRows(String nomeCorretor, List<Row> rows) {
        Map<String, Seguradora> seguradoras = new TreeMap<>();
        List<SaldoSeguradoraDTO> saldoSeguradoras = new ArrayList<>();
        double estornos = 0;
        double creditos = 0;
        double total = 0;
        double totalPremioLiquido = 0;
        int totalLancamentos = 0;

        /*TODO
        Implementar a logica para quando tiver debito ou credito manual pelo Segfy, onde não tem nenhuma CIA.
        */
        for (Row row : rows) {
            double premioLiqudo = row.getCell(7).getNumericCellValue();
            double valor = row.getCell(11).getNumericCellValue();
            Cell cia = row.getCell(12);

            String nomeSeguradora = formatter.formatCellValue(cia);
            Seguradora seguradora = seguradoras.computeIfAbsent(nomeSeguradora, Seguradora::new);
            seguradora.addSaldo(valor);
            seguradora.addPremioLiquido(premioLiqudo);
            seguradora.addQuantidade();

            if (valor > 0.0) {
                creditos += valor;
            } else {
                estornos += valor;
            }

            total += valor;
            totalPremioLiquido += premioLiqudo;
            totalLancamentos++;
        }

        seguradoras.values().forEach(seguradora -> saldoSeguradoras.add(new SaldoSeguradoraDTO(
                seguradora.getNome(),
                BigDecimal.valueOf(seguradora.getPremioLiquido()).setScale(2, RoundingMode.HALF_UP),
                BigDecimal.valueOf(seguradora.getSaldo()).setScale(2, RoundingMode.HALF_UP),
                seguradora.getQuantidade())

        ));

        return new SaldoCorretorDTO(
                this.corretorService.findByName(nomeCorretor),
                totalLancamentos,
                BigDecimal.valueOf(totalPremioLiquido).setScale(2, RoundingMode.HALF_UP),
                BigDecimal.valueOf(creditos).setScale(2, RoundingMode.HALF_UP),
                BigDecimal.valueOf(estornos).setScale(2, RoundingMode.HALF_UP),
                BigDecimal.valueOf(total).setScale(2, RoundingMode.HALF_UP),
                saldoSeguradoras
        );
    }
}
