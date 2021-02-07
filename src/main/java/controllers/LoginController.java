package controllers;

import static app.RequestUtil.getQueryLoginRedirect;
import static app.RequestUtil.getQueryPassword;
import static app.RequestUtil.getQueryPasswordSignin;
import static app.RequestUtil.getQueryUsername;
import static app.RequestUtil.getQueryUsernameSignin;
import static app.RequestUtil.removeSessionAttrLoggedOut;
import static app.RequestUtil.removeSessionAttrLoginRedirect;

import java.util.HashMap;
import java.util.Map;

import app.Path;
import app.ViewUtil;
import spark.Request;
import spark.Response;
import spark.Route;
import validadorContrasenias.RegistrarUsuario;
import validadorContrasenias.ValidadorContrasenias;
import validadorDeCompras.Usuario;

public class LoginController {

	public static Route serveLoginPage = (Request request, Response response) -> {
		Map<String, Object> model = new HashMap<>();
		model.put("loggedOut", removeSessionAttrLoggedOut(request));
		model.put("loginRedirect", removeSessionAttrLoginRedirect(request));
		return ViewUtil.render(request, model, Path.Template.LOGIN);
	};

	public static Route handleLoginPost = (Request request, Response response) -> {
		Map<String, Object> model = new HashMap<>();
		if (getQueryUsername(request) != null && getQueryPassword(request) != null) {
			if (!UserController.authenticate(getQueryUsername(request), getQueryPassword(request))) {
				model.put("authenticationFailed", true);
				return ViewUtil.render(request, model, Path.Template.LOGIN);
			}
			model.put("authenticationSucceeded", true);
			request.session().attribute("currentUser", getQueryUsername(request));
			if (getQueryLoginRedirect(request) != null) {
				response.redirect(getQueryLoginRedirect(request));
			}
			return ViewUtil.render(request, model, Path.Template.LOGIN);
		}
		else {
			
			String usernameSignin = getQueryUsernameSignin(request);
			String passwordSignin = getQueryPasswordSignin(request);
			if (!ValidadorContrasenias.getInstance().validar(passwordSignin)) {
				model.put("signinFailedSecurity", true);
				return ViewUtil.render(request, model, Path.Template.LOGIN);
			}
			Usuario userEncontrado = Usuario.buscarUsuarioBD(usernameSignin);
			if (userEncontrado != null) {
				model.put("signinFailedUsername", true);
				return ViewUtil.render(request, model, Path.Template.LOGIN);
			}
			
			RegistrarUsuario.getInstance().registrar(usernameSignin, passwordSignin);
			model.put("signinSucceeded", true);
			request.session().attribute("currentUser", usernameSignin);
			if (getQueryLoginRedirect(request) != null) {
				response.redirect(getQueryLoginRedirect(request));
			}
			return ViewUtil.render(request, model, Path.Template.LOGIN);
		}
		
	};

	public static Route handleLogoutPost = (Request request, Response response) -> {
		request.session().removeAttribute("currentUser");
		request.session().attribute("loggedOut", true);
		response.redirect(Path.Web.LOGIN);
		return null;
	};

	// The origin of the request (request.pathInfo()) is saved in the session so
	// the user can be redirected back after login
	public static void ensureUserIsLoggedIn(Request request, Response response) {
		if (request.session().attribute("currentUser") == null) {
			request.session().attribute("loginRedirect", request.pathInfo());
			response.redirect(Path.Web.LOGIN);
		}
	};



}
