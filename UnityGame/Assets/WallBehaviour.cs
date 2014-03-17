using UnityEngine;
using System.Collections;

public class WallBehaviour : MonoBehaviour, IGridObject {

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
	
	}

	#region IGridObject implementation
	
	public GridSquare ParentSquare {get; set;}

	public void Strike (LaserMove laser)
	{
		Destroy(laser.gameObject);
	}

	#endregion
}
