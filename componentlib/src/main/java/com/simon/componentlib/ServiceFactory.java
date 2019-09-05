package com.simon.componentlib;

import com.simon.componentlib.service.ILoginService;
import com.simon.componentlib.service.IMineService;

public class ServiceFactory {

    private static class Holder {
        private static final ServiceFactory INSTANCE = new ServiceFactory();
    }

    public static ServiceFactory getInstance() {
        return ServiceFactory.Holder.INSTANCE;
    }


    private ILoginService mLogin;

    private IMineService mMine;

    public ILoginService getmLogin() {
        return mLogin;
    }

    public void setmLogin(ILoginService mLogin) {
        this.mLogin = mLogin;
    }

    public IMineService getmMine() {
        return mMine;
    }

    public void setmMine(IMineService mMine) {
        this.mMine = mMine;
    }
}
