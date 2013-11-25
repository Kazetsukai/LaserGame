﻿using UnityEngine;
using System.Collections;

public class GridObject : MonoBehaviour {
	
	public GridSquare ParentSquare;
	
	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
	
	}
	
	public void Strike(LaserMove laser)
	{
		gameObject.BroadcastMessage("Interact", laser, SendMessageOptions.DontRequireReceiver);
	}
}
