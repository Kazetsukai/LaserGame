using UnityEngine;
using System.Collections;
using System.Linq;
using System;

public class LaserCombiner : MonoBehaviour {

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
        var lasers = FindObjectsOfType<LaserMove>();

        var lasersToJoin = lasers.GroupBy(l => new { l.TerminalSquare, l.Direction }).Where(g => g.Count() > 1);

        foreach (var laserGroup in lasersToJoin)
        {
            var primary = laserGroup.First();
            primary.Colour = new Color(
                Math.Min(laserGroup.Sum(l => l.Colour.r), 1),
                Math.Min(laserGroup.Sum(l => l.Colour.g), 1),
                Math.Min(laserGroup.Sum(l => l.Colour.b), 1)
            );

            laserGroup.Skip(1).ToList().ForEach(l => Destroy(l.gameObject));
        }
	}
}
