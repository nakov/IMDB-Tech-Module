using System.Linq;
using System.Net;
using System.Web.Mvc;
using IMDB.Models;

namespace IMDB.Controllers
{
    [ValidateInput(false)]
    public class FilmController : Controller
    {
        private IMDBDbContext db = new IMDBDbContext();

        [HttpGet]
        [Route("")]
        public ActionResult Index()
        {
            var films = db.Films.ToList();
            return View(films);
        }

        [HttpGet]
        [Route("create")]
        public ActionResult Create()
        {
            return View(new Film());
        }

        [HttpPost]
        [Route("create")]
        [ValidateAntiForgeryToken]
        public ActionResult Create(Film film)
        {
            if (this.ModelState.IsValid)
            {
                db.Films.Add(film);
                db.SaveChanges();
                return Redirect("/");
            }

            return View(film);
        }

        [HttpGet]
        [Route("edit/{id}")]
        public ActionResult Edit(int? id)
        {
            var film = db.Films.Find(id);
            if (film == null)
                return HttpNotFound();

            return View(film);
        }

        [HttpPost]
        [Route("edit/{id}")]
        [ValidateAntiForgeryToken]
        public ActionResult EditConfirm(int? id, Film filmModel)
        {
            var filmFromDb = db.Films.Find(id);
            if (filmFromDb == null)
                return HttpNotFound();

            if (this.ModelState.IsValid)
            {
                filmFromDb.Name = filmModel.Name;
                filmFromDb.Genre = filmModel.Genre;
                filmFromDb.Director = filmModel.Director;
                filmFromDb.Year = filmModel.Year;

                db.SaveChanges();

                return Redirect("/");
            }

            return View("Edit", filmModel);
        }

        [HttpGet]
        [Route("delete/{id}")]
        public ActionResult Delete(int? id)
        {
            var film = db.Films.Find(id);
            if (film == null)
                return HttpNotFound();

            return View(film);
        }

        [HttpPost]
        [Route("delete/{id}")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirm(int? id)
        {
            var filmFromDb = db.Films.Find(id);
            if (filmFromDb == null)
                return HttpNotFound();

            db.Films.Remove(filmFromDb);
            db.SaveChanges();

            return Redirect("/");
        }
    }
}