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
			var tileDocking = rayHit.collider.gameObject.GetComponent<GridSquare>();
			if(tileDocking!=null)
			{
				if (Input.GetMouseButtonDown(0))
				{
	                tileDocking.SetTileObject(Turret.gameObject);
				}
	            else if (Input.GetMouseButtonDown(1))
	            {
	                tileDocking.SetTileObject(Turret2.gameObject);
	            }
	            else if (Input.GetMouseButtonDown(2))
	            {
	                tileDocking.ClearTileObject();
	            }
			}
		}
		
		
	}
}
