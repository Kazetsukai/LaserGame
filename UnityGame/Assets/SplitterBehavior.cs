using UnityEngine;
using System.Collections;
using System;

public class SplitterBehavior : MonoBehaviour, IGridObject
{
    // Use this for initialization
    void Start()
    {
        transform.position += new Vector3(0, 0.4f, 0);
    }

    // Update is called once per frame
    void Update()
    {

    }

    public GridSquare ParentSquare { get; set; }

    public void Strike(LaserMove laser)
    {
        Destroy(laser.gameObject);

        LaserMove l1 = ((GameObject)Instantiate(laser.gameObject)).GetComponent<LaserMove>();
        l1.light.enabled = true;
        l1.Direction = laser.Direction.RotateLeft();
        l1.Colour = new Color(laser.Colour.r, 0, 0);
        l1.Move();


        LaserMove l2 = ((GameObject)Instantiate(laser.gameObject)).GetComponent<LaserMove>();
        l2.light.enabled = true;
        l2.Direction = laser.Direction;
        l2.Colour = new Color(0, laser.Colour.g, 0);
        l2.Move();


        LaserMove l3 = ((GameObject)Instantiate(laser.gameObject)).GetComponent<LaserMove>();
        l3.light.enabled = true;
        l3.Direction = laser.Direction.RotateRight();
        l3.Colour = new Color(0, 0, laser.Colour.b);
        l3.Move();

        if (GetEnergy(l1) < 0.2f) Destroy(l1.gameObject);
        if (GetEnergy(l2) < 0.2f) Destroy(l2.gameObject);
        if (GetEnergy(l3) < 0.2f) Destroy(l3.gameObject);
    }

    private float GetEnergy(LaserMove l1)
    {
        return l1.Colour.r + l1.Colour.g + l1.Colour.b;
    }
}


public static class GridDirectionExtensions
{
    public static GridDirection RotateLeft(this GridDirection dir)
    {
        return (GridDirection)(((int)dir + 3) % 4);
    }
    public static GridDirection RotateRight(this GridDirection dir)
    {
        return (GridDirection)(((int)dir + 1) % 4);
    }
}
