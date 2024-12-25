package mapper;

import binance.vo.QuoteVO;
import entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuoteMapper {

    @Insert("INSERT IGNORE INTO quote(open_time, open, high, low, close, volume, close_time, quote_asset_volume, trades, taker_buy_base_asset_volume, taker_buy_quote_asset_volume) " +
            "VALUES(#{openTime}, #{open}, #{high}, #{low}, #{close}, #{volume}, #{closeTime}, #{quoteAssetVolume}, #{trades}, #{takerBuyBaseAssetVolume}, #{takerBuyQuoteAssetVolume})")
    void insertQuote(QuoteVO quoteVO);
}

