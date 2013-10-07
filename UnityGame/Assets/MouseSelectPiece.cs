using UnityEngine;
using System.Collections;
using System;

public class MouseSelectPiece : MonoBehaviour {

    public Transform Turret;
    public Transform Turret2;

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
		var ray = Camera.main.ScreenPointToRay(Input.mousePosition);
		
		RaycastHit rayHit;
		
		if (Physics.Raycast(ray, out rayHit))
		{
			if (rayHit.collider.gameObject.name == "Cube(Clone)")
			{
				rayHit.collider.gameObject.renderer.material.SetColor("_Color", Color.red);
				
				if (Input.GetMouseButtonDown(0))
				{
                    rayHit.collider.gameObject.BroadcastMessage("SetTileObject",Turret.gameObject);
				}
                else if (Input.GetMouseButtonDown(1))
                {
                    rayHit.collider.gameObject.BroadcastMessage("SetTileObject", Turret2.gameObject);
                }
                else if (Input.GetMouseButtonDown(2))
                {
                    rayHit.collider.gameObject.BroadcastMessage("ClearTileObject");
                }
			}
		}
		
		
	}
}
