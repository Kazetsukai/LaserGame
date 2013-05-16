package lasergame;

import lasergame.vectormath.Vector2;

public interface IPhysicsEntity extends ILevelEntity {
	void impulse(Vector2 impulse);
	Vector2 getCentreOfMass();
}
