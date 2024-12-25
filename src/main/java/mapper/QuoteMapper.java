package mapper;

import binance.vo.QuoteVO;
import entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuoteMapper {

    @Insert({
            "<script>",
            "INSERT IGNORE INTO ${tableName} (open_time, open, high, low, close, volume, close_time, quote_asset_volume, trades, taker_buy_base_asset_volume, taker_buy_quote_asset_volume)",
            "VALUES (#{quoteVO.openTime}, #{quoteVO.open}, #{quoteVO.high}, #{quoteVO.low}, #{quoteVO.close}, #{quoteVO.volume}, #{quoteVO.closeTime}, #{quoteVO.quoteAssetVolume}, #{quoteVO.trades}, #{quoteVO.takerBuyBaseAssetVolume}, #{quoteVO.takerBuyQuoteAssetVolume})",
            "</script>"
    })
    void insertQuote(@Param("tableName") String tableName, @Param("quoteVO") QuoteVO quoteVO);
}

