using UnityEngine;
using System.Collections;

public class LaserMove : MonoBehaviour {
	
	public Vector3 From;
	public Vector3 To;
	public GridSquare TerminalSquare;
	public GridDirection Direction;
	
	public float Progress = 0;
	public float Speed = 1;
	
	// Use this for initialization
	void Start ()
	{
		transform.position = From;
	}
	
	void Update(){}//nothing
	
	// Update is called once per frame
	public void Update(float deltaTime) 
	{
		Progress += deltaTime * Speed;
		if (Progress > 1)
		{
			Progress = 1;
			TerminalSquare.Notify(this);
		}
		
        transform.position = (1 - Progress) * From + Progress * To;
	}
}
