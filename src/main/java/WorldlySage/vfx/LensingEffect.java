package WorldlySage.vfx;

import WorldlySage.MainModfile;
import basemod.interfaces.ScreenPostProcessor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

import java.nio.charset.StandardCharsets;

public class LensingEffect implements ScreenPostProcessor {
    public static ShaderProgram sp = new ShaderProgram(SpriteBatch.createDefaultShader().getVertexShaderSource(), Gdx.files.internal(MainModfile.makePath("shaders/lensing.frag")).readString(String.valueOf(StandardCharsets.UTF_8)));
    public static float time;
    public float x, y;
    public float size;

    public LensingEffect(float x, float y) {
        updatePosition(x, y);
        this.size = 1000f;
    }

    public void updatePosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void shrinkSize() {
        size -= 1000 * Gdx.graphics.getRawDeltaTime();
        if (size < 0) {
            size = 0;
        }
    }

    @Override
    public void postProcess(SpriteBatch sb, TextureRegion textureRegion, OrthographicCamera orthographicCamera) {
        time += Gdx.graphics.getRawDeltaTime();
        sb.setColor(Color.WHITE);
        sb.setBlendFunction(GL20.GL_ONE, GL20.GL_ZERO);
        ShaderProgram back = sb.getShader();
        sb.setShader(sp);
        sp.setUniformf("x_time", time);
        sp.setUniformf("u_mouse", x, y);
        sp.setUniformf("size", size);
        sb.draw(textureRegion, 0, 0);
        sb.setShader(back);
    }
}
