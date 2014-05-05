using System;
using UnityEngine;

public static class InterfaceUtility
{
	public static T GetScript<T>(this GameObject me) where T : class
	{
		foreach (var component in me.GetComponents<MonoBehaviour>())
		{
			if (component is T) 
				return component as T;
		}
		
		return null;
	}
}
