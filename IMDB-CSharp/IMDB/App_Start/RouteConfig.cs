using System.Web.Mvc;
using System.Web.Routing;

namespace IMDB
{
	public class RouteConfig
	{
		public static void RegisterRoutes(RouteCollection routes)
		{
			routes.IgnoreRoute("{resource}.axd/{*pathInfo}");

			routes.MapMvcAttributeRoutes();
		}
	}
}
