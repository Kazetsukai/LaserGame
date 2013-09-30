using UnityEngine;
using System.Collections;

public class Gridify : MonoBehaviour {
	
	public Transform piece;
	
	// Use this for initialization
	void Start () {
		
		for (int x = 0; x < 10; x++)
		{
			Instantiate(piece, new Vector3(x, 0, 0), Quaternion.identity);
		}
	}
	
	// Update is called once per frame
	void Update () {
	
	}
}
