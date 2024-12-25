package binance.socket;


import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class BinanceWebSocket {
    private static final String BASE_WS_URL = "wss://stream.binance.com:9443/ws/";

    /**
     * e: 24hrTicker，事件类型，表示这是 24 小时行情数据。
     * E: 事件发生的时间戳（毫秒），即数据的生成时间。例如 1734942442312。
     * s: BTCUSDT，交易对符号，即比特币（BTC）和美元（USDT）的交易对。
     * p: -1276.81000000，24 小时内价格变动的差值（价格变化量）。负值表示价格下降。
     * P: -1.323，24 小时内价格变动的百分比。例如，价格下降了 1.323%。
     * w: 95459.52448744，24 小时内的加权平均价格。
     * x: 96524.01000000，最后成交的价格。
     * c: 95247.19000000，当前价格，即最新的成交价格。
     * Q: 0.02062000，当前价格的成交量，即在该价格上成交的数量。
     * b: 95247.18000000，买一价，即当前订单簿上最高的买入价格。
     * B: 3.46806000，买一价的数量，即在买一价上挂单的数量。
     * a: 95247.19000000，卖一价，即当前订单簿上最低的卖出价格。
     * A: 0.82860000，卖一价的数量，即在卖一价上挂单的数量。
     * o: 96524.00000000，24 小时的开盘价。
     * h: 97351.57000000，24 小时内的最高价。
     * l: 93700.42000000，24 小时内的最低价。
     * v: 24409.70732000，24 小时内的交易量。
     * q: 2330139053.64472190，24 小时内的成交金额，通常以 USDT 计量。
     * O: 1734856042312，24 小时内开盘时的时间戳（毫秒）。
     * C: 1734942442312，24 小时内当前数据的时间戳（毫秒）。
     * F: 4319979706，24 小时内的第一笔成交记录 ID。
     * L: 4324232973，24 小时内的最后一笔成交记录 ID。
     * n: 4253268，24 小时内的成交笔数。
     * @param args
     */
    public static void main(String[] args) {
        String symbol = "btcusdt"; // 替換成目標交易對
        String streamUrl = BASE_WS_URL + symbol + "@ticker";

        try {
            WebSocketClient client = new WebSocketClient(new URI(streamUrl)) {
                @Override
                public void onOpen(ServerHandshake handshake) {
                    System.out.println("WebSocket 已連接");
                }

                @Override
                public void onMessage(String message) {
                    System.out.println("收到數據: " + message);
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    System.out.println("WebSocket 已關閉");
                }

                @Override
                public void onError(Exception ex) {
                    ex.printStackTrace();
                }
            };

            client.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
