using System;
using UnityEngine;
using System.Collections;

public class Mirror : MonoBehaviour, IGridObject {
	
	private MirrorOrientation _orientation = MirrorOrientation.BackSlash;
	
	public GridSquare ParentSquare {get; set;}
	
	// Use this for initialization
	void Start () {
		transform.position += new Vector3(0, 0.4f, 0);
		UpdateDirection();
	}
	
	// Update is called once per frame
	void Update () {
		
	}
	
	public void Strike(LaserMove laser)
	{
		if(_orientation == MirrorOrientation.BackSlash)
		{
			switch(laser.Direction)
			{
				case GridDirection.East:
					laser.Direction = GridDirection.South;	
					break;
				case GridDirection.West:
					laser.Direction = GridDirection.North;
					break;
				case GridDirection.North:
					laser.Direction = GridDirection.West;
					break;
				case GridDirection.South:
					laser.Direction = GridDirection.East;
					break;
				default:
					throw new ArgumentException("Mellon exception");
			}
		}
		else
		{
			switch(laser.Direction)
			{
				case GridDirection.East:
					laser.Direction = GridDirection.North;	
					break;
				case GridDirection.West:
					laser.Direction = GridDirection.South;
					break;
				case GridDirection.North:
					laser.Direction = GridDirection.East;
					break;
				case GridDirection.South:
					laser.Direction = GridDirection.West;
					break;
				default:
					throw new ArgumentException("Mellon exception");
			}
		}			
	}
	
	public void Flip()
	{
		if (_orientation == MirrorOrientation.BackSlash)
			_orientation = MirrorOrientation.ForwardSlash;
		else
			_orientation = MirrorOrientation.BackSlash;
		
		UpdateDirection();
	}
	
	private void UpdateDirection()
	{
		var angle = _orientation == MirrorOrientation.BackSlash ? 45f : -45f;
		transform.rotation = Quaternion.AngleAxis(angle, Vector3.up);
	}
}


public enum MirrorOrientation{
	BackSlash,
	ForwardSlash
}