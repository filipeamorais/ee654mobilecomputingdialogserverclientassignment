package com.example.serverclientassignment;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class ComputeService extends Service {
    public ComputeService()  {    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
    private final IMyAidlInterface.Stub mBinder =
            new IMyAidlInterface.Stub() {

                @Override
                public String add( ) throws RemoteException {
                    return ("hi");
                }
            };
}
