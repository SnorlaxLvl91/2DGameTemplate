package main.battle;

import main.Graphic;
import main.ScreenAdapter;

import java.awt.*;

public class BattleSprite {

    public String texture;
    public int x;
    public int y;
    public int opacity;
    public boolean blinking;

    public BattleSprite(String texture, int x, int y) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.opacity = 255;
        this.blinking = false;

        /** white shader that will turn a sprite completely white when used;
         allows us to brightly blink the sprite when it 's acting
        self.whiteShader = love.graphics.newShader[[
        extern float WhiteFactor;

        vec4 effect (vec4 vcolor, Image tex, vec2 texcoord, vec2 pixcoord)
        {
            vec4 outputcolor = Texel(tex, texcoord) * vcolor;
            outputcolor.rgb += vec3(WhiteFactor);
            return outputcolor;
        }*/
    }

    public void update(float dt) {

    }

    public void render(Graphics2D g2d) {
        /**love.graphics.setColor(255, 255, 255, self.opacity)

         -- if blinking is set to true, we'll send 1 to the white shader, which will
         -- convert every pixel of the sprite to pure white
         love.graphics.setShader(self.whiteShader)
         self.whiteShader:send('WhiteFactor', self.blinking and 1 or 0)*/

        ScreenAdapter.drawImage(g2d, Graphic.textures.get(texture), x, y);

        /**reset shader
         love.graphics.setShader()*/
    }
}
