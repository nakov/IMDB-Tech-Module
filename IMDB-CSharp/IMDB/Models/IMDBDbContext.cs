using System.Data.Entity;

namespace IMDB.Models
{
    public class IMDBDbContext : DbContext
    {
        public virtual IDbSet<Film> Films { get; set; }

        public IMDBDbContext() : base("IMDB")
        {
        }
    }
}