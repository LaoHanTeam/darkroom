package org.shareGame.depository;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author baihui.lbh
 * @version $Id: Depository.java, v 0.1 2014年8月18日 下午2:34:22 baihui.lbh Exp $
 */
public class Depository {
    private static Log                            log        = LogFactory.getLog(Depository.class);
    //使用单例模式的仓库对象
    private final static Depository               instance   = new Depository();
    private final static HashMap<String, Integer> depository = new HashMap<String, Integer>();

    private Depository() {

    }

    public static Depository getInstance() {
        return instance;
    }

    /**
     * 添加资源到仓库中，若该资源不存在则新增资源，若存在则增加库存数目,按传入的参数生产资源（包括类型和数量）
     * @param res 资源类型
     * @return 入库是否成功
     */
    public static boolean produceDepository(ResourceItem res) {
        boolean flag = false;
        if (res != null && !res.isItemEmpty()) {
            synchronized (instance) {
                displayDepository();
                if (depository.containsKey(res.getResourceName())) {
                    Integer sum = depository.get(res.getResourceName()) + res.getResourceNum();
                    depository.put(res.getResourceName(), sum);
                } else {
                    depository.put(res.getResourceName(), res.getResourceNum());
                }
                flag = true;
                System.out.println("产生" + res.getResourceNum() + "单位" + res.getResourceName());
                displayDepository();
            }
        }
        return flag;
    }

    /**
     * 按入参要求从仓库中消耗对应种类对应数目的资源
     * @param res
     * @return
     */
    public static boolean consumeDepository(ResourceItem res) {
        boolean flag = false;
        if (res != null && !res.isItemEmpty()) {
            synchronized (instance) {
                displayDepository();
                if (depository.containsKey(res.getResourceName())
                    && depository.get(res.getResourceName()) >= res.getResourceNum()) {
                    if (depository.get(res.getResourceName()) > res.getResourceNum()) {
                        Integer diff = depository.get(res.getResourceName()) - res.getResourceNum();
                        depository.put(res.getResourceName(), diff);
                        System.out.println("消耗" + res.getResourceNum() + "单位"
                                           + res.getResourceName());
                    } else {
                        depository.remove(res.getResourceName());//消耗过后资源数目为零直接删除该资源
                        System.out.println("消耗" + res.getResourceNum() + "单位"
                                           + res.getResourceName());
                    }
                } else {
                    //System.err.println("资源不存在或数量不足！");
                    log.info("资源不存在或数量不足！");
                }

                displayDepository();
            }
        }
        return flag;
    }

    private static void displayDepository() {
        System.out.println(depository);
    }

}
