/*
 *
 *    Copyright (C) Gleidson Neves da Silveira
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package io.github.gleidsonmt.dashboardfx.core.app.services;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import org.jetbrains.annotations.NotNull;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class LoadViews extends Task<String> {

    private final StringBuilder builder = new StringBuilder();
    private final List<ViewComposer> yamlViews;
    private final Label lblInfo;
    private final Context context;

    public LoadViews(Context context, Label lblInfo) {
        this.context = context;
        this.lblInfo = lblInfo;
        Yaml yaml = new Yaml(new Constructor(List.class));
        yamlViews = yaml.load(getClass().getResourceAsStream(
                "/views.yml"));
    }


    @Override
    protected String call() throws Exception {

        for (ViewComposer viewComposer : yamlViews) {
            Thread.sleep(1000);

            Platform.runLater(() -> {
                lblInfo.setText("Loading view.. " + viewComposer.getName());
                loadView(viewComposer);
            });

            Thread.sleep(1000);
        }
        return null;
    }

    private void loadView(@NotNull ViewComposer view) {

        FXMLLoader loader = new FXMLLoader();
        URL location = null;

        String path = "/views";

        if (view.getDirectory() != null) {
            builder.append("/").append(view.getDirectory());
        }

        if (view.getViews() != null) {
            for (ViewComposer v : view.getViews()) {
                if (v.getFxml() != null) {

                    location = getClass().getResource(path + builder + "/"
                            + v.getFxml());
                }
                v.setRoot(view);
                loadView(v);
            }
        }

        if (view.getDirectory() == null) location = LoadViews.class.getResource(path + builder + "/"
                + view.getFxml());
        else if (view.getFxml() != null && view.getDirectory() != null)
            location = getClass().getResource(path + builder + "/"
                    + view.getFxml());

        if (view.getDirectory() != null) {
            String act = builder.substring(builder.lastIndexOf("/") + 1, builder.length());
            if (act.equals(view.getDirectory())) builder.delete(builder.lastIndexOf("/"), builder.length());
        }

        if (location != null && view.getFxml() != null) {


            loader.setLocation(location);
//            loader.setCharset(StandardCharsets.UTF_8);
//            loader.setResources(App2.INSTANCE.getResourceBundle());

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

//            context.getRoutes().addView(new IView(view, loader));
            context.routes().addView(new IView(view, loader));

        } else if (view.getFxml() != null) {
            IOException io = new IOException("The fxml with ["
                    + view.getName() + "]" + " doesn't correspond.");
            io.printStackTrace();
        }
    }
}
