package com.koolearn.club.utils;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;


/**
 * Created by lvyangjun on 2018/3/27.
 */

public class DistributeLock {


    private static final String ROOT_NODE = "/club";//根节点

    private static final String LEARNING_WARN_NODE = "/learning_warn";//锁节点

    private ZooKeeper zooKeeper;

    private int sessionTimeout; //会话超时时间

    public DistributeLock() throws IOException, InterruptedException {
        this.zooKeeper = ZookeeperClient.getInstance();
        this.sessionTimeout = ZookeeperClient.getSessionTimeout();
    }

    /**
     * 删除锁
     *
     * @return
     */
    public boolean unlock(int learningId) {

        try {
            String dataNode = ROOT_NODE + LEARNING_WARN_NODE;
            if (zooKeeper.exists(dataNode + "/" + learningId, true) == null) {
                zooKeeper.delete(dataNode + "/" + learningId, -1);
            }
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        } finally {
            try {
                zooKeeper.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


}
