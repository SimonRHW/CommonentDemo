package com.simon.componentlib.manager;

import android.app.Activity;

import java.util.Stack;

/**
 * @author Simon
 * @date 2019/6/28
 * Function:在Application中用registerActivityLifecycleCallbacks进行Activity生命周期的栈管理
 */
public class AppActivityManager {

    private static final String TAG = AppActivityManager.class.getSimpleName();

    /**
     * activity存储栈
     */
    private Stack<Activity> sActivityStack;

    private AppActivityManager() {
    }

    /**
     * 单一实例
     */
    private static class Holder {
        private static final AppActivityManager INSTANCE = new AppActivityManager();
    }

    public static AppActivityManager getInstance() {
        return AppActivityManager.Holder.INSTANCE;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (sActivityStack == null) {
            sActivityStack = new Stack<>();
        }
        sActivityStack.add(activity);
    }


    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        if (sActivityStack != null && !sActivityStack.isEmpty()) {
            Activity activity = sActivityStack.lastElement();
            finishActivity(activity);
        }
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (sActivityStack == null) {
            return;
        }
        if (activity != null && !activity.isFinishing()) {
            sActivityStack.remove(activity);
            activity.finish();
        }
    }

    /**
     * 移除指定的Activity
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        if (sActivityStack == null) {
            return;
        }
        if (activity != null) {
            sActivityStack.remove(activity);
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        if (sActivityStack == null) {
            return;
        }
        for (Activity activity : sActivityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        if (sActivityStack == null) {
            return;
        }
        for (int i = 0, size = sActivityStack.size(); i < size; i++) {
            if (null != sActivityStack.get(i)) {
                sActivityStack.get(i).finish();
            }
        }
        sActivityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public void appExit(Boolean isBackground) {
        try {
            //finish所有activity
            finishAllActivity();
            //杀死进程
            android.os.Process.killProcess(android.os.Process.myPid());
        } catch (Exception ignored) {
        } finally {
            if (!isBackground) {
                System.exit(0);
            }
        }
    }

}
