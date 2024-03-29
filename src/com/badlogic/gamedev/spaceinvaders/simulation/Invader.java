package com.badlogic.gamedev.spaceinvaders.simulation;

import java.io.Serializable;

public class Invader implements Serializable
{	
	public static float INVADER_RADIUS = 0.75f;
	public static float INVADER_VELOCITY = 1;
	public static int INVADER_POINTS = 40;
	public final static int STATE_MOVE_LEFT = 0;
	public final static int STATE_MOVE_DOWN = 1;
	public final static int STATE_MOVE_RIGHT = 2;
	
	public final Vector position = new Vector();
	public int state = STATE_MOVE_LEFT;
	public boolean wasLastStateLeft = true;
	public float movedDistance = Simulation.PLAYFIELD_MAX_X / 2;;	
	
    public Invader( Vector position )
    {
       this.position.set( position );
    } 
	
	public void update(float delta, float speedMultiplier) 
	{			
		movedDistance += delta * INVADER_VELOCITY * speedMultiplier;
		if( state == STATE_MOVE_LEFT )
		{
			position.x -= delta * INVADER_VELOCITY * speedMultiplier;
			if( movedDistance > Simulation.PLAYFIELD_MAX_X )
			{
				state = STATE_MOVE_DOWN;
				movedDistance = 0;
				wasLastStateLeft = true;
			}
		}
		if( state == STATE_MOVE_RIGHT )
		{
			position.x += delta * INVADER_VELOCITY * speedMultiplier;
			if( movedDistance > Simulation.PLAYFIELD_MAX_X )
			{
				state = STATE_MOVE_DOWN;
				movedDistance = 0;
				wasLastStateLeft = false;
			}
		}
		if( state == STATE_MOVE_DOWN )
		{
			position.z += delta * INVADER_VELOCITY * speedMultiplier;
			if( movedDistance > 1 )
			{
				if( wasLastStateLeft )
					state = STATE_MOVE_RIGHT;
				else
					state = STATE_MOVE_LEFT;
				movedDistance = 0;
			}
		}		
	}
}
