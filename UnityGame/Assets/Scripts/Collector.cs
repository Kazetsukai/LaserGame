using UnityEngine;
using System.Collections;
using System;

public class Collector : MonoBehaviour, IGridObject
 {
 	public float Omnomnom;
 
	#region IGridObject implementation
	
	public GridSquare ParentSquare { get; set; }

	public void Strike (LaserMove laser)
	{
		Destroy(laser.gameObject);
        var diff = Diff(laser.Colour, this.renderer.material.color);
        if (Math.Round(diff, 0) != 0.0)
            Omnomnom -= diff;
        else
            Omnomnom += 1;
	}

    private float Diff(Color you, Color me)
    {
        return Math.Abs(me.r - you.r) + Math.Abs(me.g - you.g) + Math.Abs(me.b - you.b);
        //return 0.5f - totalDiff;
    }

	#endregion

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
        if (Omnomnom < 1) Omnomnom = 1;
	    float s = 1 + Omnomnom*0.1f;
	
	    this.transform.localScale = new Vector3(s, s, s);
	}
}