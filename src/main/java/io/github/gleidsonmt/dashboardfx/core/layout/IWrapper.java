/*
 *    Copyright (C) Gleidson Neves da Silveira
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package io.github.gleidsonmt.dashboardfx.core.layout;

import io.github.gleidsonmt.dashboardfx.core.app.interfaces.Wrapper;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.Alert;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.Dialog;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.Drawer;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.Popup;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  05/10/2022
 */
public class IWrapper extends StackPane implements Wrapper {

    public IWrapper() {
        reset();
        setId("wrapper");
    }

    public void reset() {
        setBackground(
                new Background(
                        new BackgroundFill(
                                Color.gray(0.5, 0.3),
                                CornerRadii.EMPTY,
                                Insets.EMPTY)
                )
        );
    }

    private Popup popup;

    @Override
    public Popup getPopup() {
        if (dialog == null) popup = new Popup(this);
        return popup;
    }

    private Dialog dialog;
    @Override
    public Dialog getDialog() {
        if (dialog == null) dialog = new Dialog(this);
        return dialog;
    }

    private Alert alert;

    @Override
    public Alert getAlert() {
        if (alert == null) alert = new Alert(this);
        return alert;
    }

    private Drawer drawer;

    @Override
    public Drawer getDrawer() {
        if (drawer == null) drawer = new Drawer(this);
        return drawer;
    }

    public enum WrapperBackgroundType {
        NONE, GRAY, BLUR;
    }
}
