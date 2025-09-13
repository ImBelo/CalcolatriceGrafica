package com.github.imbelo.calcolatricegrafica.model.graph;

import com.github.imbelo.calcolatricegrafica.model.interfaces.Camera;
import com.github.imbelo.calcolatricegrafica.model.interfaces.CartesianPoint;
import com.github.imbelo.calcolatricegrafica.model.interfaces.PointConverter;
import com.github.imbelo.calcolatricegrafica.model.interfaces.ScreenPoint;

public class CameraPointConverter implements PointConverter {
    private final Camera camera;
    private final double cameraWidth;
    private final double cameraHeight;
    private final int HeightPixels;
    private final int WidthPixels;
    private final CartesianPoint center;
    private final double right;
    private final double bottom;

    public CameraPointConverter(Camera camera) {
        this.camera = camera;
        this.cameraHeight = camera.getCameraHeight();
        this.cameraWidth = camera.getCameraWidth();
        this.HeightPixels = camera.getHeightPixels();
        this.WidthPixels = camera.getWidthPixels();
        this.center = camera.getCenter();
        this.right = center.x() - cameraWidth/2;
        this.bottom = center.y() - cameraHeight/2;

    }

    @Override
    public CartesianPointImpl toCartesian(ScreenPoint screenPoint) {
        int screenX = screenPoint.x();
        int screenY = screenPoint.y();
        return new CartesianPointImpl(
            screenX / (double)WidthPixels * cameraWidth + right,
            (HeightPixels - screenY) / (double)HeightPixels * cameraHeight + bottom);
    }
    @Override
    public ScreenPointImpl toScreen(CartesianPoint cartesianPoint) {
        double x = cartesianPoint.x();
        double y = cartesianPoint.y();
        int screenX = (int) ((x - right) / cameraWidth * WidthPixels);
        int screenY=  HeightPixels -  (int)((y - bottom) / cameraHeight * HeightPixels
        );
        return new ScreenPointImpl(screenX,screenY);
    }

}
