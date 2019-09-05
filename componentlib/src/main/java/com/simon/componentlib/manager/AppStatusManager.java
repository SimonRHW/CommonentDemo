package com.simon.componentlib.manager;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Simon
 * @date 2019/6/28
 * Function:应用状态管理类
 */
public class AppStatusManager implements Application.ActivityLifecycleCallbacks {

    private static final String TAG = AppStatusManager.class.getSimpleName();
    private int activityCount;
    private WeakReference<Activity> mCurrentActivity;
    /**
     * 缓存需要监听应用状态的页面
     */
    private static final Set<BackGroundCallback> CALL_BACK_CACHE = new HashSet<>();
    /**
     * 是否在前台
     */
    private boolean isForeground;

    private AppStatusManager() {
    }

    /**
     * 静态内部类的优点是：外部类加载时并不需要立即加载内部类，内部类不被加载则不去初始化INSTANCE，故而不占内存。
     * 即当AppStatusManager第一次被加载时，Holder，只有当getInstance()方法第一次被调用时，才会去初始化INSTANCE
     * 第一次调用getInstance()方法会导致虚拟机加载Holder类，这种方法不仅能确保线程安全，也能保证单例的唯一性，同时也延迟了单例的实例化。
     */
    private static class Holder {
        private static final AppStatusManager INSTANCE = new AppStatusManager();
    }

    public static AppStatusManager getInstance() {
        return Holder.INSTANCE;
    }

    public void init(Application app) {
        app.registerActivityLifecycleCallbacks(this);
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, Bundle savedInstanceState) {
        AppActivityManager.getInstance().addActivity(activity);
    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
        activityCount++;
        isForeground = true;
    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
        mCurrentActivity = new WeakReference<>(activity);
    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {
        mCurrentActivity.clear();
        mCurrentActivity = null;
    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {
        activityCount--;
        if (activityCount == 0) {
            isForeground = false;
            if (!CALL_BACK_CACHE.isEmpty()) {
                for (BackGroundCallback backGroundCallback : CALL_BACK_CACHE) {
                    backGroundCallback.onAppStatusChange();
                }
            }
        }
    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @Nullable Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
        AppActivityManager.getInstance().removeActivity(activity);
    }

    /**
     * @return App 是否在前台
     * @see #isAppBackground()
     */
    public boolean isAppForeground() {
        return isForeground;
    }

    /**
     * @return App 是否在后台
     * @see #isAppForeground()
     */
    public boolean isAppBackground() {
        return !isForeground;
    }

    /**
     * 获取当前显示的activity
     *
     * @return 当前resume的activity
     */
    public Activity getCurrentActivity() {
        return mCurrentActivity.get();
    }


    public void addObserver(@NonNull BackGroundCallback observer) {
        CALL_BACK_CACHE.add(observer);

    }

    public void removeObserver(@NonNull BackGroundCallback observer) {
        CALL_BACK_CACHE.remove(observer);

    }

    public interface BackGroundCallback {
        void onAppStatusChange();
    }

}
