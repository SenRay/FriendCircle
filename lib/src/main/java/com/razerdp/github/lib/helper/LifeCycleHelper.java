package com.razerdp.github.lib.helper;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * Created by 大灯泡 on 2019/8/1.
 */
public class LifeCycleHelper<T> implements LifecycleObserver {
    T obj;
    LifecycleOwner mOwner;
    Callback<T> mCallback;

    public LifeCycleHelper(T obj, LifecycleOwner owner, Callback<T> callback) {
        this.obj = obj;
        mOwner = owner;
        mCallback = callback;
        mOwner.getLifecycle().addObserver(this);
    }

    public static <T> void handle(T obj, Object owner, Callback<T> callback) {
        if (!(owner instanceof LifecycleOwner)) return;
        new LifeCycleHelper<T>(obj, (LifecycleOwner) owner, callback);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate() {
        if (mCallback != null) {
            mCallback.onCreate(obj);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onStart() {
        if (mCallback != null) {
            mCallback.onStart(obj);
        }
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void onResume() {
        if (mCallback != null) {
            mCallback.onResume(obj);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void onPause() {
        if (mCallback != null) {
            mCallback.onPause(obj);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void onStop() {
        if (mCallback != null) {
            mCallback.onStop(obj);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy() {
        if (mCallback != null) {
            mCallback.onDestroy(obj);
        }
        mCallback = null;
        obj = null;
        mOwner.getLifecycle().removeObserver(this);
        mOwner = null;
    }

    public interface Callback<T> {
        void onCreate(T obj);

        void onStart(T obj);

        void onResume(T obj);

        void onPause(T obj);

        void onStop(T obj);

        void onDestroy(T obj);
    }

    public static abstract class CallbackAdapter<T> implements Callback<T>{
        @Override
        public void onCreate(T obj) {

        }

        @Override
        public void onStart(T obj) {

        }

        @Override
        public void onResume(T obj) {

        }

        @Override
        public void onPause(T obj) {

        }

        @Override
        public void onStop(T obj) {

        }

        @Override
        public void onDestroy(T obj) {

        }
    }
}
