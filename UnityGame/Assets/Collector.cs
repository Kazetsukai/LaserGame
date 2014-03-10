using UnityEngine;
using System.Collections;

public class Collector : MonoBehaviour, IGridObject
 {
 	public int Omnomnom;
 
	#region IGridObject implementation

	public void Strike (LaserMove laser)
	{
		Destroy(laser.gameObject);
		
		Omnomnom++;
	}

	#endregion

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
	float s = 1 + Omnomnom*0.1f;
	
	this.transform.localScale = new Vector3(s, s, s);
	}
}