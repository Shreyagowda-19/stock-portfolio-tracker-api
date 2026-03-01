package com.shreya.stockportfolio.service;

import com.shreya.stockportfolio.model.Stock;
import com.shreya.stockportfolio.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import com.shreya.stockportfolio.dto.PortfolioSummary;
@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    // Add a new stock
    public Stock addStock(Stock stock) {
        return stockRepository.save(stock);
    }

    // Get all stocks
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    // Get single stock by ID
    public Optional<Stock> getStockById(Long id) {
        return stockRepository.findById(id);
    }

    // Update a stock
    public Stock updateStock(Long id, Stock updatedStock) {

        Stock existingStock = stockRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stock not found with id " + id));

        existingStock.setStockName(updatedStock.getStockName());
        existingStock.setTickerSymbol(updatedStock.getTickerSymbol());
        existingStock.setQuantity(updatedStock.getQuantity());
        existingStock.setBuyPrice(updatedStock.getBuyPrice());

        return stockRepository.save(existingStock);
    }

    // Delete a stock
    public void deleteStock(Long id) {
        stockRepository.deleteById(id);
    }
    public PortfolioSummary getPortfolioSummary() {

        List<Stock> stocks = stockRepository.findAll();

        double totalInvestment = 0;
        double currentValue = 0;

        for (Stock stock : stocks) {

            double investment = stock.getBuyPrice() * stock.getQuantity();
            totalInvestment += investment;

            // Temporary mock current price (10% increase)
            double currentPrice = stock.getBuyPrice() * 1.10;
            currentValue += currentPrice * stock.getQuantity();
        }

        double totalProfit = currentValue - totalInvestment;

        return new PortfolioSummary(totalInvestment, currentValue, totalProfit);
    }
    public List<String> getProfitPerStock() {

        List<Stock> stocks = stockRepository.findAll();
        List<String> result = new ArrayList<>();

        for (Stock stock : stocks) {

            double currentPrice = stock.getBuyPrice() * 1.10;
            double profit = (currentPrice - stock.getBuyPrice()) * stock.getQuantity();
            profit = Math.round(profit * 100.0) / 100.0;

            result.add(stock.getStockName() + " Profit: " + profit);
        }

        return result;
    }
}