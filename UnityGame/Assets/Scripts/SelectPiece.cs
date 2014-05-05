using UnityEngine;
using System.Collections;
using System;

public class SelectPiece : MonoBehaviour {

    public Transform Turret;
    public Transform Mirror;
    public Transform Splitter;

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
                if (!Input.GetKey(KeyCode.LeftControl) && !Input.GetKey(KeyCode.RightControl))
                {
                    if (Input.GetKeyDown(KeyCode.T))
                    {
                        tileDocking.SetTileObject(Turret.gameObject);
                    }
                    else if (Input.GetMouseButtonDown(0))
                    {
                        tileDocking.SetTileObject(Mirror.gameObject);
                    }
                    else if (Input.GetKeyDown(KeyCode.Delete))
                    {
                        tileDocking.ClearTileObject();
                    }
                    else if (Input.GetKeyDown(KeyCode.S))
                    {
                        tileDocking.SetTileObject(Splitter.gameObject);
                    }
                }
			}
		}
		
		
	}
}
