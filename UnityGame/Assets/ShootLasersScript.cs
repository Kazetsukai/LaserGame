using UnityEngine;
using System.Collections;

public class ShootLasersScript : MonoBehaviour {

	// Use this for initialization
	void Start () {
	
	}
	
	
	
	// Update is called once per frame
	void Update () {
        var ray = Camera.main.ScreenPointToRay(Input.mousePosition);
        var laserShooter = GameObject.Find("LaserShooter");
        var floor = GameObject.Find("Floor");

        Plane p = new Plane(new Vector3(0, 1, 0), laserShooter.transform.position);
        float dist = 0f;

        p.Raycast(ray, out dist);

        // Get point on plane at mouse
        var pointOnPlane = ray.GetPoint(dist);
        Vector3 pos = laserShooter.transform.position;
		
    	FireMahLazar(pos, pointOnPlane - pos);
	}

    void FireMahLazar(Vector3 pos, Vector3 dir)
    {
        RaycastHit rayCastInfo;
        Ray laserRay = new Ray(pos, dir);

        bool hitSomething = Physics.Raycast(laserRay, out rayCastInfo);
		
        if (hitSomething)
        {
			if(rayCastInfo.rigidbody != null)
			rayCastInfo.rigidbody.AddForceAtPosition(laserRay.direction * 50, rayCastInfo.point);
			
            Vector3 reflectDir = Vector3.Reflect(rayCastInfo.point - pos, rayCastInfo.normal);

            Debug.DrawLine(pos, rayCastInfo.point);

            Debug.Log(rayCastInfo.collider.gameObject);

            FireMahLazar(rayCastInfo.point, reflectDir);
        }
        else
        {
            Debug.DrawLine(pos, laserRay.GetPoint(100));
        }
    }
}
