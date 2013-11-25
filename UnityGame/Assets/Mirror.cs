using UnityEngine;
using System.Collections;

public class Mirror : MonoBehaviour, IGridObject {

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
	
	}
	
	public void Strike(LaserMove laser)
	{
		laser.Direction = (GridDirection)Random.Range(0, 4);
	}
}
