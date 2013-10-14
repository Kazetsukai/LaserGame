using UnityEngine;
using System.Collections;

public class TurretShooter : MonoBehaviour {

    float time = 0;

    public Object LaserObject;

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
        time += Time.deltaTime;

        Debug.Log(time);

        if (time > 1)
        {
            time--;

            var laser = Instantiate(LaserObject) as GameObject;
            laser.transform.position = this.transform.position + new Vector3(0, 0.5f, 0);
        }
	}
}
