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

package io.github.gleidsonmt.dashboardfx.core.app.interfaces;

import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.Dialog;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.Drawer;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.Popup;
import io.github.gleidsonmt.dashboardfx.core.layout.conteiners.interfaces.IAlert;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  04/09/2022
 */
@SuppressWarnings("ClassEscapesDefinedScope")
public interface Wrapper {

//    PopupOver getPopOver();
    Popup getPopup();

    Dialog getDialog();

    IAlert getAlert();

    Drawer getDrawer();

}
