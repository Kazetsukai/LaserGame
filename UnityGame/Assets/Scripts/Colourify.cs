using UnityEngine;
using System.Collections;

public class Colourify : MonoBehaviour {

	// Use this for initialization
	void Start () {
		renderer.material.SetColor("_Color", Color.white);
	}
	
	// Update is called once per frame
	void Update () {

	}
}
