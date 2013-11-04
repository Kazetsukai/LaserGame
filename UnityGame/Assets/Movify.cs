using UnityEngine;
using System.Collections;

public class Movify : MonoBehaviour {
	
	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
		if (_moving)
			transform.position += new Vector3(0, Time.deltaTime, 0);
	}
	
	bool _moving = false;
	
	void GoAway()
	{
		_moving = true;
	}
}
