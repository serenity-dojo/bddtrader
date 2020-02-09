package net.bddtrader.acceptancetests.screenplay.endpoints;

public enum BDDTraderEndPoints {
    RegisterClient("/client"),
    ClientPortfolio("/client/{clientId}/portfolio"),
    ClientPortfolioPositions("/client/{clientId}/portfolio/positions"),
    PortfolioPositions("/portfolio/{portfolioId}/positions"),
    PlaceOrder("/portfolio/{portfolioId}/order"),
    Portfolio("/portfolio/{portfolioId}"),
    UpdatePrice("/stock/{securityCode}/price");

    private final String path;

    BDDTraderEndPoints(String path) {
        this.path = path;
    }

    public String path() {
        return path;
    }
}
