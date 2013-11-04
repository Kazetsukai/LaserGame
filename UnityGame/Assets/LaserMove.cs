using UnityEngine;
using System.Collections;

public class LaserMove : MonoBehaviour {
	
	public Vector3 From;
	public Vector3 To;
	
	public float Progress = 0;
	public float Speed = 1;
	
	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
		Progress += Time.deltaTime * Speed;
		if (Progress > 1)
			Progress = 1;
		
        transform.position = (1-Progress) * From + (Progress) * To;
	}
}
