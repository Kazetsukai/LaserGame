using UnityEngine;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using Assets.LevelModel;
using UnityEditor;
using System.Xml.Serialization;
using System.IO;
using System.Xml;
using System.Text;

public class LevelLoadSave : MonoBehaviour {

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
        if (Input.GetKeyDown(KeyCode.F5))
        {
            SaveGame();
        }
        if (Input.GetKeyDown(KeyCode.F6))
        {
            LoadGame();
        }
	}

    private void LoadGame()
    {
        Level model;

        XmlSerializer serializer = new XmlSerializer(typeof(Level));
        using (var reader = new XmlTextReader("level.xml"))
        {
            model = serializer.Deserialize(reader) as Level;
        }

        MakeGrid(model);
    }

    private void SaveGame()
    {
        var model = BuildLevelModel();

        XmlSerializer serializer = new XmlSerializer(typeof(Level));
        using (var writer = new XmlTextWriter("level.xml", Encoding.UTF8))
        {
            writer.Formatting = Formatting.Indented;
            serializer.Serialize(writer, model);
        }

        Debug.Log("Level saved");
    }

    private Level BuildLevelModel()
    {
        var levelObjs = new List<LevelObject>();

        var gridObjects = FindObjectsOfType<MonoBehaviour>().OfType<IGridObject>();
        foreach (IGridObject g in gridObjects)
        {
            var obj = ((MonoBehaviour)g).gameObject;
            var name = obj.name.Replace("(Clone)", ""); // HACK: Need a nice way to identify object prefabs
            levelObjs.Add(new LevelObject() { Name = name, X = g.ParentSquare.X, Y = g.ParentSquare.Y });
        }

        return new Level() { Objects = levelObjs.ToArray() };
    }

    private void MakeGrid(Level model)
    {
        var gridSquares = FindObjectsOfType<GridSquare>();
        foreach (var gridSquare in gridSquares)
        {
            gridSquare.ClearTileObject();
        }

        foreach (var levelObj in model.Objects)
        {
            var name = "GridObjects/" + levelObj.Name;
            Debug.Log("Loading - " + name);
            var gameObj = Resources.Load<GameObject>(name) as GameObject;

            gridSquares.Where(g => g.X == levelObj.X && g.Y == levelObj.Y).Single().SetTileObject(gameObj);
        }
    }
}
