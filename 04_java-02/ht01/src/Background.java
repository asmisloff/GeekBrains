import java.awt.*;

public class Background {

    public Background(float r, float g, float b, float dr, float dg, float db) {
        setStartColor(r, g, b);
        setGradient(dr, dg, db);
    }

    public void setStartColor(float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public void setGradient(float dr, float dg, float db) {
        float norm = (float) Math.sqrt(dr*dr + dg*dg + db*db);
        this.dr = dr / norm;
        this.dg = dg / norm;
        this.db = db / norm;
    }

    public void update(float deltaTime) {
        r = (r + (dr * velocity * deltaTime)) % 1f;
        g = (g + (dg * velocity * deltaTime)) % 1f;
        b = (b + (db * velocity * deltaTime)) % 1f;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    public Color getColor() {
        return new Color(r, g, b);
    }

    private float r, g, b; /* координаты цвета */
    private float dr, dg, db; /* координаты вектора-градиента цвета в пространстве rgb */
    private float velocity = 0.2f; /* скорость изменения цвета в условных единицах */
}
