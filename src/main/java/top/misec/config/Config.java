package top.misec.config;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import top.misec.utils.LoadFileResource;

/**
 * Auto-generated: 2020-10-13 17:10:40
 *
 * @author Junzhou Liu
 * @create 2020/10/13 17:11
 */
public class Config {

    static Logger logger = (Logger) LogManager.getLogger(Config.class.getName());


    /**
     * 每日设定的投币数 [0,5]
     */
    private int numberOfCoins;

    /**
     * 投币时是否点赞 [0,1]
     */
    private int selectLike;

    /**
     * 观看时是否分享 [0,1]
     */
    private int watchAndShare;

    /**
     * 年度大会员自动充电[false,true]
     */
    private boolean monthEndAutoCharge;

    /**
     * 执行客户端操作时的平台 [ios,android]
     */
    private String devicePlatform;

    /**
     * 投币优先级 [0,1]
     * 0：优先给热榜视频投币，1：优先给关注的up投币
     */
    private int coinAddPriority;

    /**
     * 硬币投给关注的UP最近发布 [0,1] 暂不引入
     * coinAddPriority 为 1 时生效
     * 0: 忽略
     * 1: 生效
     */
    //private int coinAddToUpRecentVideo;

    private static Config CONFIG = new Config();

    private Config() {
    }

    public static Config getInstance() {
        return CONFIG;
    }

    public String getDevicePlatform() {
        return devicePlatform;
    }

    public int getSelectLike() {
        return selectLike;
    }

    public int getWatchAndShare() {
        return watchAndShare;
    }


    public int getCoinAddPriority() {
        return coinAddPriority;
    }

//     public void setCoinAddPriority(int coinAddPriority) {
//         this.coinAddPriority = coinAddPriority;
//     }

//     public int getCoinAddToUpRecentVideo() {
//         return coinAddToUpRecentVideo;
//     }

//     public void setCoinAddToUpRecentVideo(int coinAddToUpRecentVideo) {
//         this.coinAddToUpRecentVideo = coinAddToUpRecentVideo;
//     }

    public boolean isMonthEndAutoCharge() {
        return monthEndAutoCharge;
    }

    public void setNumberOfCoins(int numberOfCoins) {
        this.numberOfCoins = numberOfCoins;
    }

    public int getNumberOfCoins() {
        return numberOfCoins;
    }


    @Override
    public String toString() {
        return "Config{" +
                "numberOfCoins=" + numberOfCoins +
                ", selectLike=" + selectLike +
                ", watchAndShare=" + watchAndShare +
                ", monthEndAutoCharge=" + monthEndAutoCharge +
                ", devicePlatform='" + devicePlatform + '\'' +
                ", coinAddPriority=" + coinAddPriority +
                '}';
    }

    public String outputConfig() {
        String outputConfig = "您设置的每日投币数量为: ";
        outputConfig += numberOfCoins;

        if (coinAddPriority == 1) {
           // if (coinAddToUpRecentVideo == 1) {
                //outputConfig += " 优先给关注的up最近更新的视频投币";
           // } else {
                outputConfig += " 优先给关注的up投币";
            //}
        } else {
            outputConfig += " 优先给热榜视频投币";
        }

        if (selectLike == 1) {
            outputConfig += " 投币时是否点赞: " + "是";
        } else {
            outputConfig += " 投币时是否点赞: " + "否";
        }

        return outputConfig + " 执行app客户端操作的系统是: " + devicePlatform;
    }

    /**
     * 优先从jar包同级目录读取
     * 读取配置文件 src/main/resources/config.json
     */
    public void configInit() {
        String configJson = null;
        String outConfig = LoadFileResource.loadConfigJsonFromFile();
        if (outConfig != null) {
            configJson = outConfig;
            logger.info("读取外部配置文件成功");
        } else {
            logger.info("读取配置文件成功");
            configJson = LoadFileResource.loadConfigJsonFromAsset();
        }

        Config.CONFIG = new Gson().fromJson(configJson, Config.class);

        logger.info(Config.getInstance().outputConfig());
    }
}
