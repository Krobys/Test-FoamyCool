package com.akrivonos.beerdictionaryapplication.presenters;

import android.view.View;

import com.akrivonos.beerdictionaryapplication.BeerModelData;
import com.akrivonos.beerdictionaryapplication.interfaces.mvp_listeners.presenter_listeners.BreweriesPresenterListener;
import com.akrivonos.beerdictionaryapplication.interfaces.mvp_listeners.view_control_listeners.BreweryViewListener;
import com.akrivonos.beerdictionaryapplication.models.BreweryDetailedDescription;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class BreweryPresenter implements BreweriesPresenterListener {
    private final BeerModelData beerModelData;
    private Disposable observerDisposable;
    private BreweryViewListener viewPresenterListener;

    public BreweryPresenter(BeerModelData beerModelData, BreweryViewListener breweryViewListener) {
        this.beerModelData = beerModelData;
        Observer<ArrayList<BreweryDetailedDescription>> observerBrewery = new Observer<ArrayList<BreweryDetailedDescription>>() {
            @Override
            public void onSubscribe(Disposable d) {
                observerDisposable = d;
            }

            @Override
            public void onNext(ArrayList<BreweryDetailedDescription> breweryModels) {
                if (breweryModels.size() != 0) {
                    viewPresenterListener.showBreweryList(breweryModels);
                    viewPresenterListener.setVisibilityEmptyMessage(View.GONE);
                } else {
                    viewPresenterListener.setVisibilityEmptyMessage(View.VISIBLE);
                }
                viewPresenterListener.setVisibilityProgressBar(View.GONE);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {

            }
        };
        beerModelData.setBreweryObserverRetrofit(observerBrewery);
        this.viewPresenterListener = breweryViewListener;
    }

    @Override
    public void loadBreweryList(LatLng latLng) {
        viewPresenterListener.setVisibilityProgressBar(View.VISIBLE);
        beerModelData.loadBreweryListRetrofit(latLng);
    }

    @Override
    public void detachView() {
        observerDisposable.dispose();
    }
}