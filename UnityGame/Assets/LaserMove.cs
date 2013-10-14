using UnityEngine;
using System.Collections;

public class LaserMove : MonoBehaviour {

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
        transform.position = transform.position + new Vector3(Time.deltaTime, 0, 0);
	}
}
