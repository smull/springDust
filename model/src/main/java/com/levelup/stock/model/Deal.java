package com.levelup.stock.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "DEAL")
public class Deal implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    Long id;

    @Column(name = "USER_ID")
    Long userId;

    private String type;
    private String ticket;
    private String symbol;

    private double lots;
    private String buySell;
    private double openPrice;
    private double closePrice;

    @Temporal(TemporalType.TIMESTAMP)
    private Date openTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date closeTime;

    private double profit;
    private double swap;
    private String commission;
    private double tp;
    private double sl;
    private double pips;
    private String result;
    private double tradeDuration;
    private String magicNumber;
    private String orderComment;
    private double mae;
    private double mfe;
    private String fXBlueLiveAccount;


    public Deal() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }


    public double getLots() {
        return lots;
    }

    public void setLots(double lots) {
        this.lots = lots;
    }

    public String getBuySell() {
        return buySell;
    }

    public void setBuySell(String buySell) {
        this.buySell = buySell;
    }

    public double getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(double openPrice) {
        this.openPrice = openPrice;
    }

    public double getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(double closePrice) {
        this.closePrice = closePrice;
    }

    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public double getSwap() {
        return swap;
    }

    public void setSwap(double swap) {
        this.swap = swap;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }

    public double getTp() {
        return tp;
    }

    public void setTp(double tp) {
        this.tp = tp;
    }

    public double getSl() {
        return sl;
    }

    public void setSl(double sl) {
        this.sl = sl;
    }

    public double getPips() {
        return pips;
    }

    public void setPips(double pips) {
        this.pips = pips;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public double getTradeDuration() {
        return tradeDuration;
    }

    public void setTradeDuration(double tradeDuration) {
        this.tradeDuration = tradeDuration;
    }

    public String getMagicNumber() {
        return magicNumber;
    }

    public void setMagicNumber(String magicNumber) {
        this.magicNumber = magicNumber;
    }

    public String getOrderComment() {
        return orderComment;
    }

    public void setOrderComment(String orderComment) {
        this.orderComment = orderComment;
    }

    public double getMae() {
        return mae;
    }

    public void setMae(double mae) {
        this.mae = mae;
    }

    public double getMfe() {
        return mfe;
    }

    public void setMfe(double mfe) {
        this.mfe = mfe;
    }

    public String getfXBlueLiveAccount() {
        return fXBlueLiveAccount;
    }

    public void setfXBlueLiveAccount(String fXBlueLiveAccount) {
        this.fXBlueLiveAccount = fXBlueLiveAccount;
    }
}
