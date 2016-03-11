package chap5;

/**
 * Created by hjy on 16-1-10.
 * 告警功能入口类
 * 模式角色：Two-phaseTermination.ThreadOwner
 */
public class AlarmMgr {
    //保存AlarmMgr类的唯一实例
    private static final AlarmMgr INSTANCE = new AlarmMgr();

    private volatile boolean shutdownRequested = false;

  /*  //告警发送线程
    private final*/
}
