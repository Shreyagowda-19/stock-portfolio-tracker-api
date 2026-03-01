package com.shreya.stockportfolio.controller;
import com.shreya.stockportfolio.dto.PortfolioSummary;
import com.shreya.stockportfolio.model.Stock;
import com.shreya.stockportfolio.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    @Autowired
    private StockService stockService;

    // POST - Add a stock
    @PostMapping
    public Stock addStock(@RequestBody Stock stock) {
        return stockService.addStock(stock);
    }

    // GET - Get all stocks
    @GetMapping
    public List<Stock> getAllStocks() {
        return stockService.getAllStocks();
    }

    // GET - Get stock by ID
    @GetMapping("/{id}")
    public Optional<Stock> getStockById(@PathVariable Long id) {
        return stockService.getStockById(id);
    }

    // PUT - Update a stock
    @PutMapping("/stocks/{id}")
    public Stock updateStock(@PathVariable Long id, @RequestBody Stock stock) {
        return stockService.updateStock(id, stock);
    }

    // DELETE - Delete a stock
    @DeleteMapping("/{id}")
    public String deleteStock(@PathVariable Long id) {
        stockService.deleteStock(id);
        return "Stock deleted successfully!";
    }
    @GetMapping("/portfolio/summary")
    public PortfolioSummary getSummary() {
        return stockService.getPortfolioSummary();
    }

    @GetMapping("/profit")
    public List<String> getProfit() {
        return stockService.getProfitPerStock();
    }
}