package com.wallet.tienda.controller;

import com.wallet.tienda.dto.response.ReportDTORes;
import com.wallet.tienda.service.IReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Tag(name = "Controlador de reportes")
@RestController
@RequestMapping("/api/v1/reports")
public class ReportController {

    @Autowired
    private IReportService reportService;

    /**
     *
     * @return
     */
    @Operation(
            summary = "Trae el balance de compra-ventas",
            description = "Trae el balance de compra venta y devuelve una respuesta http con el dto de reporte"
    )
    @GetMapping()
    public ResponseEntity<ReportDTORes> getBalance(){
        return ResponseEntity.ok(reportService.balance());
    }

    @Operation(
            summary = "Trae el balance de compra-ventas mensual",
            description = "Trae el balance de compra venta mensual y devuelve una respuesta http con el dto de reporte"
    )
    @GetMapping("/months")
    public ResponseEntity<Map<String, Double>> getMonthBalance(){
        return ResponseEntity.ok(reportService.balanceOfMonths());
    }


}
