package com.adriansoft.flappybird.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Animation {
    private Array<TextureRegion> frames;
    private float maxFrameTime;
    private float currenFrameTime;
    private int frameCount;
    private int frame;

    public Animation(TextureRegion region ,int framCount ,float cycleTime){
        frames = new Array<TextureRegion>();
        int framewidth = region.getRegionWidth() / framCount;
        for (int i=0; i < framCount; i++){
            frames.add(new TextureRegion(region ,i * framewidth, 0,framewidth ,region.getRegionHeight()));
        }
        this.frameCount = framCount;
        maxFrameTime = cycleTime / frameCount;
        frame = 0;
    }

    public void update(float dt) {
        currenFrameTime += dt;
        if (currenFrameTime > maxFrameTime){
            frame++;
            currenFrameTime = 0;

        }
        if (frame >= frameCount){
            frame = 0;
        }
    }
    public TextureRegion getFrame(){
        return frames.get(frame);
    }
}
