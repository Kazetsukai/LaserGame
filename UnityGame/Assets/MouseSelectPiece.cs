using UnityEngine;
using System.Collections;

public class MouseSelectPiece : MonoBehaviour {

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
					rayHit.collider.gameObject.BroadcastMessage("GoAway");
				}
			}
		}
		
		
	}
}
